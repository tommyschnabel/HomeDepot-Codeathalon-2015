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
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult About()
        {
            ViewBag.Message = "Your application description page.";

            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }
        [HttpPost]
        public JsonResult mLogin()
        {

            return Json(0, JsonRequestBehavior.AllowGet);
        }
        [HttpPost]
        public JsonResult RetrieveMapDetails()
        {

            return Json(0, JsonRequestBehavior.AllowGet);
        }
        [HttpPost]
        public JsonResult mRetrieveMapDetails()
        {

            return Json(0, JsonRequestBehavior.AllowGet);
        }
        public JsonResult mSubmitItemSelection(List<BuildViewItem> viewItem)
        {
            viewItem = new List<BuildView
            return Json(0, JsonRequestBehavior.AllowGet);
        }
    }
}