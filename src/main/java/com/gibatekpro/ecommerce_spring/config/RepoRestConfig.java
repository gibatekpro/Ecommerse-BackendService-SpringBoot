package com.gibatekpro.ecommerce_spring.config;

import com.gibatekpro.ecommerce_spring.checkout.entity.Order;
import com.gibatekpro.ecommerce_spring.entity.Country;
import com.gibatekpro.ecommerce_spring.entity.Product;
import com.gibatekpro.ecommerce_spring.entity.ProductCategory;
import com.gibatekpro.ecommerce_spring.entity.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class RepoRestConfig implements RepositoryRestConfigurer {
    //This is used to configure read and write http requests

    private final EntityManager entityManager;

    //set allowed origins
//    @Value("${allowed.origins}")
//    private String[] allowedOrigins;

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    @Autowired
    public RepoRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] httpMethodsList = {HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.PATCH};
        //Config for Products Class
        disableHttpMethod(config, httpMethodsList, Product.class);

        //Config for Products Category Class
        disableHttpMethod(config, httpMethodsList, ProductCategory.class);

        //Config for Country Class
        disableHttpMethod(config, httpMethodsList, Country.class);

        //Config for State Class
        disableHttpMethod(config, httpMethodsList, State.class);

        //Config for Order Class
        disableHttpMethod(config, httpMethodsList, Order.class);

        //We are exposing Ids because Spring Data rest does not
        //provide the id in the JSON data
        exposeIds(config);

        // configure cors mapping
        // with this, we don't need to add @CrossOrigin to every Repository
//        cors.addMapping(basePath + "/**")
//                .allowedOrigins(allowedOrigins) // Adjust the origin URL to match your Angular application
//                .allowedMethods("GET", "POST", "PUT", "DELETE") // Add the HTTP methods you want to allow
//                .allowedHeaders("*") // Add the headers you want to allow
//                .allowCredentials(true);

        //TODO: Not sure about this yet
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
    }

    private void disableHttpMethod(RepositoryRestConfiguration config, HttpMethod[] httpMethodsList, Class theClass) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(httpMethodsList))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(httpMethodsList));

    }

    private void exposeIds(RepositoryRestConfiguration config) {

        //expose entity ids

        //- gets a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        //- create an array of the entity types
        List<Class> entityClasses = new ArrayList<>();

        // - get the entity types for the entities
        for (EntityType entityType : entities) {

            entityClasses.add(entityType.getJavaType());

        }

        // - expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(entityClasses.toArray(new Class[0]));
        config.exposeIdsFor(domainTypes);

    }

    /**
     * Spring Data REST has different detection strategies
     *
     *      ALL              Exposes all Spring Data repositories regardless
     *                        of their Java visibility or annotation configuration
     *      DEFAULT          Exposes public Spring Data repositories or ones explicitly
     *                         annotated with @RepositoryRestResource and its exported
     *                         attribute not set to false
     *      VISIBILITY       Exposes only public Spring Data repositories regardless of annotation
     *                          configuration
     *      ANNOTATED         Only exposes Spring Data repositories explicitly annotated with
     *                          @RepositoryRestResource and its exported attribute not set to false
     *
     * */
}
