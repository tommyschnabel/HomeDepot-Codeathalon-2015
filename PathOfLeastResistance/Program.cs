using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace pathTesting
{

    public class djikstra 
    {
        private List<Node> allNodes;
        public djikstra(List<Node> allNodes)
        {
            this.allNodes = allNodes;
        }

        public List<Node> findPath(List<Node> destinations)
        {
            List<Node> path = new List<Node>();
            //Add the entrance node to the path
            path.Add(destinations.ElementAt(0));
            //take out redundancies
            destinations = destinations.Distinct().ToList();
            return findPath(destinations, path);
        }

        public List<Node> findPath(List<Node> destinations, List<Node> path)
        {
            Node currentNode = path.ElementAt(path.Count - 1);
            Node nextNode = null;
            double distance = 0;
            foreach (Node node in currentNode.ConnectedNodes)
            {
                if (distance == 0)
                {
                    nextNode = node;
                    distance = currentNode.calculateDistance(node);
                    continue;
                }

                Node dest = null;
                foreach(Node destination in destinations) {
                    if (node.nodeID == destination.nodeID) {
                        nextNode = node;
                        dest = destination;
                    }
                }

                if (nextNode != null && dest != null) {
                    destinations.Remove(dest);
                    break;
                }

                if (currentNode.calculateDistance(node) < distance)
                {
                    distance = currentNode.calculateDistance(node);
                    nextNode = node;
                }
            }

            path.Add(nextNode);

            if (destinations.Count == 0)
            {
                return path;
            }

            return findPath(destinations, path);
        }
    }
    public class Pathfinder
    {
        List<Node> nodeList = new List<Node>();
        public Pathfinder(List<Node> masterNodeList)
        {
            nodeList = masterNodeList;
        }
        public List<Node> FindPath(List<Node> destinations)
        {
            List<Node> path = new List<Node>();
            int pathCount;
            path.Add(destinations.ElementAt(0));
            pathCount =0;

            foreach(Node n in destinations)
            {
                Node temp;
                int pathSlot = path.Count();
                Console.WriteLine("pathSlot " + pathSlot);
                //Node placeHolder = new Node(0, 0, 0);
                //path.Insert(pathCount, placeHolder);
                temp = FindNext(path.ElementAt(pathCount), n);
                while (temp != n)
                {
                    pathSlot = path.Count();
                    Console.WriteLine("pathSlot " + pathSlot);
                    temp = FindNext(path.ElementAt(pathCount), n);
                    path.Add(temp);
                    pathCount++;
                    Console.WriteLine(path.Count.ToString());
                    Console.WriteLine(pathCount);
                }
            }
            Console.WriteLine("Path found with " + path.Count() + " links");
            foreach(Node n in path)
            {
                Console.WriteLine(n.nodeID);
            }
            return path; 
        }
        public Node FindNext(Node current, Node currentDestination)//List<Node> dList)//, Node currentDestination)
        {
            //Drew'sCode
            foreach(Node n in current.ConnectedNodes)
            {
                if (n.nodeID == currentDestination.nodeID)
                {
                    return n;
                }
                else return CheckAngles(current.ConnectedNodes, currentDestination);
            }
            return CheckAngles(current.ConnectedNodes, currentDestination);
        }
        public Node CheckAngles(List<Node> list, Node dest)
        {
            int lowestID = 0, iter = 0;
            double lowestAngle = DoTheMathStuffs(list.ElementAt(0).Lattitude, list.ElementAt(0).Longitude, dest.Lattitude, dest.Longitude); ;
              foreach(Node n in list)
             {
                  double tempAngle = DoTheMathStuffs(n.Lattitude,n.Longitude, dest.Lattitude, dest.Longitude);
                  //math
                  if (tempAngle<lowestAngle)
                  {
                      lowestID = iter;
                  }
                      iter++;
             }
              return list.ElementAt(lowestID);
        }
        public double DoTheMathStuffs(double lat1, double lon1, double lat2, double lon2)
        {
            double latDiff = lat2 - lat1;
            Console.WriteLine("latDiff: " + latDiff);
            double lonDiff = lon2 - lon1;
            Console.WriteLine("latDiff: " + lonDiff);
            Console.WriteLine("Angle: " + Math.Atan2(latDiff, lonDiff));
            return Math.Abs(Math.Atan2(latDiff, lonDiff));
        }
    }
    public class Node
    {
        public double Lattitude, Longitude;
        public int nodeID, typeID;
        public List<Node> ConnectedNodes = new List<Node>();
        public Node(double lat, double lon, int id)
        {
            Lattitude = lat;
            Longitude = lon;
            nodeID = id;
        }
        
        public double calculateDistance(Node other)
        {
            double lat2 = other.Lattitude;
            double lon2 = other.Longitude;
            double lat1 = this.Lattitude;
            double lon1 = this.Longitude;
            var R = 6372.8; // In kilometers 
            var dLat = toRadians(lat2 - lat1);
            var dLon = toRadians(lon2 - lon1);
            lat1 = toRadians(lat1);
            lat2 = toRadians(lat2);

            var a = Math.Sin(dLat / 2) * Math.Sin(dLat / 2) + Math.Sin(dLon / 2) * Math.Sin(dLon / 2) * Math.Cos(lat1) * Math.Cos(lat2);
            var c = 2 * Math.Asin(Math.Sqrt(a));
            return R * 2 * Math.Asin(Math.Sqrt(a));
        }

        public static double toRadians(double angle)
        {
            return Math.PI * angle / 180.0;
        }
    }
    //pathOutput returns only the data needed by the database: latitude, longitude, and typeID
    public struct pathOutput
    {
        public double lat, lon;
        public int typeID;

    }

/*    public static class Haversine //Copy-pasta'd from http://rosettacode.org/wiki/Haversine_formula#C.23
    {
        public static double calculate(double lat1, double lon1, double lat2, double lon2)
        {
            var R = 6372.8; // In kilometers 
            var dLat = toRadians(lat2 - lat1);
            var dLon = toRadians(lon2 - lon1);
            lat1 = toRadians(lat1);
            lat2 = toRadians(lat2);

            var a = Math.Sin(dLat / 2) * Math.Sin(dLat / 2) + Math.Sin(dLon / 2) * Math.Sin(dLon / 2) * Math.Cos(lat1) * Math.Cos(lat2);
            var c = 2 * Math.Asin(Math.Sqrt(a));
            return R * 2 * Math.Asin(Math.Sqrt(a));
        }

        public static double toRadians(double angle)
        {
            return Math.PI * angle / 180.0;
        }
    }
 * */
    class Program
    {
        static void Main(string[] args)
        {
            Node n1 = new Node(33.980869, -84.434674, 1);
            Node n2 = new Node(33.980869, -84.434729, 2);
            Node n3 = new Node(33.981007, -84.434716, 3);
            Node n4 = new Node(33.981105, -84.434920, 4);
            n1.ConnectedNodes.Add(n2);
            n1.ConnectedNodes.Add(n3);
            n2.ConnectedNodes.Add(n1);
            n2.ConnectedNodes.Add(n4);
            n3.ConnectedNodes.Add(n1);
            n3.ConnectedNodes.Add(n4);
            n4.ConnectedNodes.Add(n2);
            n4.ConnectedNodes.Add(n3);
            List<Node> nodeList = new List<Node>();
            nodeList.Add(n1);
            nodeList.Add(n2);
            nodeList.Add(n3);
            nodeList.Add(n4);
            List<Node> destinationNodes = new List<Node>();
            destinationNodes.Add(n1);
            destinationNodes.Add(n4);
            //djikstra dj = new djikstra(nodeList);
            //List<Node> finalPath = dj.findPath(destinationNodes);
            //foreach (Node n in finalPath)
            //{
              //  Console.WriteLine(n.nodeID);
            //}

            Pathfinder pf = new Pathfinder(nodeList);
            List<Node> finalPath = pf.FindPath(destinationNodes);
            foreach (Node n in finalPath)
            {
                Console.WriteLine(n.ToString());
            }
            Console.ReadLine();
            //Console.WriteLine("Node: " + n1.nodeID + " has a lattitude of: " + n1.Lattitude + " Longitude: " + n1.Longitude);
            //Console.WriteLine("Node: " + n2.nodeID + " has a lattitude of: " + n2.Lattitude + " Longitude: " + n2.Longitude);
            //Console.WriteLine(String.Format("The distance between coordinates {0},{1} and {2},{3} is: {4}", 36.12, -86.67, 33.94, -118.40, Haversine.calculate(36.12, -86.67, 33.94, -118.40)));
            //Console.WriteLine("The distance between node " + n1.nodeID + " and node " + n2.nodeID + " is: " + n1.calculateDistance(n2));
       }
        }
}



