package pl.coderslab.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import pl.coderslab.fixtures.FundFixture;
import pl.coderslab.fixtures.SecurityFixture;
import pl.coderslab.fixtures.TradeFixture;
import pl.coderslab.fixtures.UserFixture;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Slf4j
public class AppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        ctx.setServletContext(container);
        ctx.refresh();

        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

        FilterRegistration.Dynamic fr = container.addFilter("encodingFilter", new CharacterEncodingFilter());
        fr.setInitParameter("encoding", "UTF-8");
        fr.setInitParameter("forceEncoding", "true");
        fr.addMappingForUrlPatterns(null, true, "/*");

//        loadFixtures(ctx);

    }

    private void loadFixtures(AnnotationConfigWebApplicationContext ctx){

        UserFixture userFixture = ctx.getBean("userFixture", UserFixture.class);
        userFixture.loadIntoDB();
        log.trace("loading userFixture");

        FundFixture fundFixture = ctx.getBean("fundFixture", FundFixture.class);
        fundFixture.loadIntoDB();
        log.trace("loading fundFixture");

        SecurityFixture securityFixture = ctx.getBean("securityFixture", SecurityFixture.class);
        securityFixture.loadIntoDB();
        log.trace("loading securityFixture");

        TradeFixture tradeFixture = ctx.getBean("tradeFixture", TradeFixture.class);
        tradeFixture.loadIntoDB();
        log.trace("loading tradeFixture");

    }
}