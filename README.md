# Systematic Papers Review

[![Build Status](https://img.shields.io/travis/manoelcampos/papers-review/master.svg)](https://travis-ci.org/manoelcampos/papers-review) [![GPL licensed](https://img.shields.io/badge/license-GPL-blue.svg)](http://www.gnu.org/licenses/gpl-3.0)


A Java 8 web application for research papers cataloging and systematic review management.

The application enables importing papers from bibtex files to manage all their review flow and metadata collection.
It also allows the generation of reports in different formats such as HTML and LaTeX.

It's an MVC application built in the server side with [VRaptor 4 web framework](http://vraptor.org), JPA 2.1 with Hibernate 4.
Views are generated using JSP and the client side uses [Bootstrap](http://getbootstrap.com) and JQuery.

# Configuration

The application was tested only in the [Wildfly 8.1](http://wildfly.org) Application Server (AS).
An data source with the database connection configuration should be created in the AS
using the exact same name defined inside the `<non-jta-data-source>` tag of the [persistence.xml](/src/main/resources/META-INF/persistence.xml) file.
You can use any JPA supported database. Since the persistence.xml is configure to auto-create the schema, you only need to create an empty database before creating the data source.

JPA EntityManager is injected using the [vraptor-jpa](http://www.vraptor.org/en/docs/plugins/#vraptor-jpa). It
implements the *Open Session In View*, managing transactions in the application instead of letting the AS to do that.
However, this appears to be an [anti-pattern](https://vladmihalcea.com/2016/05/30/the-open-session-in-view-anti-pattern) :unamused:. I'll check this latter.

The WildFly web console usually is accessed at [http://localhost:9990](http://localhost:9990).

# Structure

The server side code is into the [src/main/java](/src/main/java) directory where:

- [model](/src/main/java/com/manoelcampos/papersreview/model) contains the business classes;
- [controller](/src/main/java/com/manoelcampos/papersreview/controller) contains the classes intermediating the interactions between views and models;
- [dto](/src/main/java/com/manoelcampos/papersreview/dto) contains Data Type Objects used gatter data in convinient ways for using in views, for instance to generate reports;
- [dao](/src/main/java/com/manoelcampos/papersreview/dao) contains the Data Access Objects with interact with the database through JPA queries.
- [service](/src/main/java/com/manoelcampos/papersreview/service) contains classes aggregating multiple DAOs used by controllers. They free a controller 
- [report](/src/main/java/com/manoelcampos/papersreview/report) contains classes allowing to build tables in different formats such as HTML and LaTeX.
- [config](/src/main/java/com/manoelcampos/papersreview/config) contains classes defining specific VRaptor configuration
and how some objects to be injected using the `@Inject` annotation should be automatically instantiated, such as DAOs and EntityManagers.

The client side code is into the [webapp](/src/main/webapp) directory where the pages (views) are 
located in the [jsp](/src/main/webapp/WEB-INF/jsp) directory. Each jsp file represents
a view for an action (method) with the same name inside the related controller.
 