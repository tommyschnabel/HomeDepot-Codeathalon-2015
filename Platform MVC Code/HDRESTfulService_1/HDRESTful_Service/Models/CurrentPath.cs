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
    public class CurrentPath
    {
        public int TypeID { get; set; }
        public string TypeName { get; set; }
        public double Lng { get; set; }
        public double Lat { get; set; }
        public int[] ID { get; set; }
    }
}