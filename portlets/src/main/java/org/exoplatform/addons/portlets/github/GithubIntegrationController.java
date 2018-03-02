package org.exoplatform.addons.portlets.github;

import juzu.*;
import juzu.impl.common.Tools;
import juzu.request.SecurityContext;
import juzu.template.Template;

import javax.inject.Inject;
import javax.portlet.PortletPreferences;
import org.exoplatform.commons.juzu.ajax.Ajax;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.organization.OrganizationService;

import org.exoplatform.web.application.RequestContext;
import org.gatein.common.text.EntityEncoder;
import org.json.JSONException;
import org.json.JSONObject;

@SessionScoped
public class GithubIntegrationController {


    private static final Log LOG = ExoLogger.getExoLogger(GithubIntegrationController.class);
    @Inject
    @Path("index.gtmpl")
    Template indexTemplate;

    @Inject
    PortletPreferences portletPreferences;

    OrganizationService organizationService_;

    @View
    public Response.Content index()
    {
        String remoteUser = RequestContext.getCurrentInstance().getRemoteUser();

        return indexTemplate.with()
                .set("repository", "country")
                .set("devBranch", "develop")
                .set("stableBranch", "stable/5.0.x")
                .ok();
    }

    @Resource (method = HttpMethod.POST)
    @Ajax
    public Response create(SecurityContext securityContext) throws JSONException {

        String currentUser = securityContext.getRemoteUser();
        if(currentUser == null) {
            return Response.status(401).body("You must login to create new project");
        }

        JSONObject result = new JSONObject();
        result.put("id", 1);//Can throw JSONException (same for all #json.put methods below)
        result.put("name", "kmenzli");
        result.put("color", "transparent");

        return Response.ok(result.toString()).withCharset(Tools.UTF_8);
    }

}
