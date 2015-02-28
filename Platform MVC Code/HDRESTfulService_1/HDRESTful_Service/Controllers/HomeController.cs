using HDRESTful_Service.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace HDRESTful_Service.Controllers
{
    public class HomeController : Controller
    {
        //public ActionResult Index()
        //{
        //    return View();
        //}

        [HttpPost]
        public JsonResult path()
        {
            List<CurrentPath> currentPath = new List<CurrentPath>();
            return Json(currentPath, JsonRequestBehavior.AllowGet);
        }
        [HttpPost]
        public JsonResult current()
        {
            
            return Json(new ItemsViewModel(), JsonRequestBehavior.AllowGet);
        }
        [HttpPost]
        public JsonResult delete(int ID)
        {
            
            return Json(new DeleteFromList(ID), JsonRequestBehavior.AllowGet);
        }
        [HttpPost]
        public JsonResult request()
        {

            return Json(new AllItemsViewModel(), JsonRequestBehavior.AllowGet);
        }
        [HttpPost]
        public JsonResult add(List<int> iDList)
        {
            new AddToList(iDList);
            //List<ItemsViewModel> viewModel = new List<ItemsViewModel>();
            return Json(new ItemsViewModel(), JsonRequestBehavior.AllowGet);
        }
    }
}