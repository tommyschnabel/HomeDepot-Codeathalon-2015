using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(HDRESTful_Service.Startup))]
namespace HDRESTful_Service
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
