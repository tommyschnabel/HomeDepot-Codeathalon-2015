using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace HDRESTful_Service.Controllers
{
    public class WebController : Controller
    {
        // GET: Web
        public ActionResult Index()
        {
            return View();
        }
    }
}