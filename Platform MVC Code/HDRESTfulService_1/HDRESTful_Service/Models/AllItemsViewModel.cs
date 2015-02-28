using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Text;
using System.Threading.Tasks;
using MongoDB.Bson;
using MongoDB.Driver;
using MongoDB.Bson.IO;
using MongoDB.Bson.Serialization;
using MongoDB.Bson.Serialization.Attributes;
using MongoDB.Bson.Serialization.Conventions;
using MongoDB.Bson.Serialization.IdGenerators;
using MongoDB.Bson.Serialization.Options;
using MongoDB.Bson.Serialization.Serializers;
using MongoDB.Driver.Builders;

namespace HDRESTful_Service.Models
{
    public class AllItemsViewModel
    {
        public static MongoClient client = new MongoClient("mongodb://128.199.79.143:12345");
        public static List<Store> stores = new List<Store>();
        public static List<BuildViewItem> currentList = new List<BuildViewItem>();

        public AllItemsViewModel()
        {
            //returns a list if BuildViewItems
            RequestAllItems();
        }
        public class BuildViewItem
        {
            public BuildViewItem(int i, string it, string tn)
            {
                itemID = i;
                itemName = it;
                typeName = tn;
            }

            public int itemID { get; set; }
            public string itemName { get; set; }
            public string typeName { get; set; }
        }

        [BsonIgnoreExtraElements]
        public class Item
        {
            public Item(int i, string it)
            {
                id = i;
                name = it;
            }

            public int id { get; set; }
            public string name { get; set; }
        }
        [BsonIgnoreExtraElements]
        public class Type
        {
            public int id { get; set; }
            public double lat { get; set; }
            public double lng { get; set; }
            public String type { get; set; }
            public String[] connectedTo { get; set; }
            public List<Item> items { get; set; }
        }
        [BsonIgnoreExtraElements]
        public class Store
        {
            public ObjectId Id { get; set; }
            public String StoreId { get; set; }
            public String Name { get; set; }
            public String Lat { get; set; }
            public String Lng { get; set; }
            public String zoom { get; set; }
            public List<Type> Type { get; set; }
        }
        static async Task AsyncGetCurrentlist()
        {
            currentList.Clear();

            var database = client.GetDatabase("test");
            var collection = database.GetCollection<Item>("currentList");
            var query = Query<Item>.NE(x => x.id, -1);
            var list = await collection.Find(query).ToListAsync();

            List<Store> stores = getStores();

            List<BuildViewItem> items = new List<BuildViewItem>();
            foreach (var item in list)
            {
                foreach (var store in stores)
                {
                    foreach (var type in store.Type)
                    {
                        foreach (var i in type.items)
                        {
                            if (i.id.Equals(item.id))
                                items.Add(new BuildViewItem(i.id, Convert.ToString(i.name), type.type));
                        }

                    }
                }
            }

            currentList.AddRange(items);
        }
        static async Task AsyncSetStores()
        {
            stores.Clear();
            var database = client.GetDatabase("test");
            var collection = database.GetCollection<Store>("Store");
            var query = Query<Store>.EQ(x => x.StoreId, "Store 111");
            var list = await collection.Find(query).ToListAsync();

            foreach (var store in list)
            {
                stores.Add(store);
            }
        }

        static List<Store> getStores()
        {
            AsyncSetStores().Wait();
            return stores;
        }

        static List<BuildViewItem> RequestAllItems()
        {
            List<Store> stores = getStores();
            List<BuildViewItem> items = new List<BuildViewItem>();
            foreach (var store in stores)
            {
                foreach (var type in store.Type)
                {
                    foreach (var i in type.items)
                    {
                        items.Add(new BuildViewItem(i.id, Convert.ToString(i.name), type.type));
                    }

                }
            }
            return items;
        }
    }
}