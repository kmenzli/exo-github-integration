package org.exoplatform.addons.portlets.github;

import juzu.Path;
import juzu.Response;
import juzu.SessionScoped;
import juzu.View;
import juzu.template.Template;

import javax.inject.Inject;
import javax.portlet.PortletPreferences;

import org.exoplatform.services.organization.OrganizationService;

import org.exoplatform.web.application.RequestContext;

@SessionScoped
public class GithubIntegrationController {

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

}
