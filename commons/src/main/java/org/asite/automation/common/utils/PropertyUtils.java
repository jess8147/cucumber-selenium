package org.asite.automation.common.utils;

import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.lib.AutomationSeleniumExtendedLibrary;
import org.asite.automation.common.resources.AdoddleCommonStringPool;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

public class PropertyUtils {

    /** The training flag.*/
    public static boolean					trainingFlag				= false;

    /**
     * Sets up Environment System Properties.
     */
    public void setEnvironmentProperties() {

        if (System.getProperty("application.url") == null)
            System.setProperty("application.url", ResourceHandler.getPropertyValue("application.url"));

        if (System.getProperty("selenium.server.hub.ip") == null)
            System.setProperty("selenium.server.hub.ip", ResourceHandler.getPropertyValue("selenium.server.hub.ip"));

        if (System.getProperty("application.url").contains(AdoddleCommonStringPool.ENV_QA1_HOST)) {
            AutomationSeleniumExtendedLibrary.configfile = ResourceHandler.getPropertyValue("qa1.environment.properties");
        }
        else if (System.getProperty("application.url").contains(AdoddleCommonStringPool.ENV_QA2_HOST)) {
            AutomationSeleniumExtendedLibrary.configfile = ResourceHandler.getPropertyValue("qa2.environment.properties");
        }
        else if (System.getProperty("application.url").contains(AdoddleCommonStringPool.ENV_LIVE_HOST)) {
            AutomationSeleniumExtendedLibrary.configfile = ResourceHandler.getPropertyValue("production.environment.properties");
        }
        else if (System.getProperty("application.url").contains(AdoddleCommonStringPool.MTA_LIVE_HOST)) {
            AutomationSeleniumExtendedLibrary.configfile = ResourceHandler.getPropertyValue("mta.environment.properties");
        }
        else {
            AutomationSeleniumExtendedLibrary.configfile = ResourceHandler.getPropertyValue("external.environment.properties");
        }
    }

    /**
     * Sets the up environment test project.
     *
     * @param dc the new up environment test project
     */
    public void setupEnvironmentTestProperties(String dc, String loggedInUser) {

        boolean adoddleApp = ResourceHandler.loadProperty("application.test.module").contains("Adoddle");

        setEnvironmentProperties();

        if (ResourceHandler.loadProperty("primary.uk.username") != null && ResourceHandler.loadProperty("primary.uk.username").contains("training"))
            trainingFlag = true;

        List<String> hosts = Arrays.asList(ResourceHandler.getPropertyValue("selenium.server.hosts").split(","));
        List<String> nodes = Arrays.asList(ResourceHandler.getPropertyValue("selenium.server.nodes").split(","));

        if (hosts.toString().contains(AutomationSeleniumExtendedLibrary.nodeIP.replace("\\\\", "")))
        {
            System.setProperty("remote.user", ResourceHandler.getPropertyValue("selenium.server.hosts.user"));
            System.setProperty("remote.password", ResourceHandler.getPropertyValue("selenium.server.hosts.password"));
        }
        else if (nodes.toString().contains(AutomationSeleniumExtendedLibrary.nodeIP.replace("\\\\", "")))
        {
            System.setProperty("remote.user", ResourceHandler.getPropertyValue("selenium.server.nodes.user"));
            System.setProperty("remote.password", ResourceHandler.getPropertyValue("selenium.server.nodes.password"));
        }

        if ("us".equalsIgnoreCase(dc))
        {
            System.setProperty("global.test.project", ResourceHandler.loadProperty("test.us.project"));
            System.setProperty("global.test.project.id", ResourceHandler.loadProperty("test.us.project.id"));
            System.setProperty("primary.username", loggedInUser);

            if(adoddleApp) {
                System.setProperty("primary.password", ResourceHandler.loadProperty("primary.us.password"));
                System.setProperty("secondary.username", ResourceHandler.loadProperty("secondary.us.username"));
                System.setProperty("secondary.password", ResourceHandler.loadProperty("secondary.us.password"));
            }
        }
        else if ("uk".equalsIgnoreCase(dc))
        {
            if (trainingFlag)
            {
                System.setProperty("global.test.project", ResourceHandler.loadProperty("test.training.project"));
                System.setProperty("primary.username", loggedInUser);
                System.setProperty("primary.password", ResourceHandler.loadProperty("primary.uk.password"));
                System.setProperty("secondary.username", ResourceHandler.loadProperty("secondary.uk.username"));
                System.setProperty("secondary.password", ResourceHandler.loadProperty("secondary.uk.password"));
            }
            else
                {
                System.setProperty("global.test.project", ResourceHandler.loadProperty("test.uk.project"));
                System.setProperty("global.test.project.id", ResourceHandler.loadProperty("test.uk.project.id"));
                System.setProperty("primary.username", loggedInUser);
                if(adoddleApp) {
                    System.setProperty("primary.password", ResourceHandler.loadProperty("primary.uk.password"));
                    System.setProperty("secondary.username", ResourceHandler.loadProperty("secondary.uk.username"));
                    System.setProperty("secondary.password", ResourceHandler.loadProperty("secondary.uk.password"));
                }
            }
        }
        else if ("aus".equalsIgnoreCase(dc) || "au".equalsIgnoreCase(dc)) {

            System.setProperty("global.test.project", ResourceHandler.loadProperty("test.aus.project"));
            System.setProperty("global.test.project.id", ResourceHandler.loadProperty("test.aus.project.id"));
            System.setProperty("primary.username", loggedInUser);

            if(adoddleApp) {
                System.setProperty("primary.password", ResourceHandler.loadProperty("primary.aus.password"));
                System.setProperty("secondary.username", ResourceHandler.loadProperty("secondary.aus.username"));
                System.setProperty("secondary.password", ResourceHandler.loadProperty("secondary.aus.password"));
            }
        }

        if(adoddleApp) {
            System.setProperty("primary.user.org", ResourceHandler.loadProperty("primary.user.org"));
            System.setProperty("global.org.id", ResourceHandler.loadProperty("primary.user.org.id"));
            System.setProperty("multi.project.username", ResourceHandler.loadProperty("multi.project.user.username"));
            System.setProperty("multi.project.password", ResourceHandler.loadProperty("multi.project.user.password"));
        }
        else {
            System.setProperty("primary.user.org", ResourceHandler.loadProperty("auto.test.classic.organization"));
            System.setProperty("global.org.id", ResourceHandler.loadProperty("auto.test.classic.org.id"));
        }

        System.setProperty("primary.username.id", ResourceUtils.getLoggedInUserID(loggedInUser ,dc));
    }

    /**
     * Sets up Configuration System Properties.
     *
     * @param dc Sets up Configuration System Properties
     */
    public void setConfigSytemProperties(String dc)
    {

        if (ResourceHandler.loadProperty("primary.uk.username") != null && ResourceHandler.loadProperty("primary.uk.username").contains("training"))
            PropertyUtils.trainingFlag = true;

        System.setProperty("selenium.server.hub.port", ResourceHandler.getPropertyValue("selenium.server.hub.port"));
        System.setProperty("multi.project.username", ResourceHandler.loadProperty("multi.project.user.id"));
        System.setProperty("multi.project.user.password", ResourceHandler.loadProperty("multi.project.user.password"));
        System.setProperty("primary.user.org", ResourceHandler.loadProperty("primary.user.org"));
        System.setProperty("secondary.user.org", ResourceHandler.loadProperty("secondary.user.org"));

        if (dc.equalsIgnoreCase("US"))
        {
            System.setProperty("global.test.project", ResourceHandler.loadProperty("test.us.project"));
            System.setProperty("primary.username", ResourceHandler.loadProperty("primary.us.username"));
            System.setProperty("primary.password", ResourceHandler.loadProperty("primary.us.password"));
            System.setProperty("secondary.username", ResourceHandler.loadProperty("secondary.us.username"));
            System.setProperty("secondary.password", ResourceHandler.loadProperty("secondary.us.password"));
            System.setProperty("current.user.username", ResourceHandler.loadProperty("current.user.us.username"));
        }
        else if (dc.equalsIgnoreCase("UK"))
        {
            if (PropertyUtils.trainingFlag) {
                System.setProperty("global.test.project", ResourceHandler.loadProperty("test.training.project"));
                System.setProperty("primary.username", ResourceHandler.loadProperty("primary.uk.username"));
                System.setProperty("primary.password", ResourceHandler.loadProperty("primary.uk.password"));
                System.setProperty("secondary.username", ResourceHandler.loadProperty("secondary.uk.username"));
                System.setProperty("secondary.password", ResourceHandler.loadProperty("secondary.uk.password"));
            }
            else {
                System.setProperty("global.test.project", ResourceHandler.loadProperty("test.uk.project"));
                System.setProperty("primary.username", ResourceHandler.loadProperty("primary.uk.username"));
                System.setProperty("primary.password", ResourceHandler.loadProperty("primary.uk.password"));
                System.setProperty("secondary.username", ResourceHandler.loadProperty("secondary.uk.username"));
                System.setProperty("secondary.password", ResourceHandler.loadProperty("secondary.uk.password"));
                System.setProperty("current.user.username", ResourceHandler.loadProperty("current.user.uk.username"));
            }
        }
        else if (dc.equalsIgnoreCase("AUS"))
        {
            System.setProperty("global.test.project", ResourceHandler.loadProperty("test.aus.project"));
            System.setProperty("primary.username", ResourceHandler.loadProperty("primary.aus.username"));
            System.setProperty("primary.password", ResourceHandler.loadProperty("primary.aus.password"));
            System.setProperty("secondary.username", ResourceHandler.loadProperty("secondary.aus.username"));
            System.setProperty("secondary.password", ResourceHandler.loadProperty("secondary.aus.password"));
            System.setProperty("current.user.username", ResourceHandler.loadProperty("current.user.aus.username"));
        }

        AdoddleCommonAppMethods.fileBetaViewFlag = ResourceHandler.loadProperty("file.view.beta.flag").equalsIgnoreCase("true") ? true : false;
    }
}
