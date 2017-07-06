# Systematic Papers Review

[![Build Status](https://img.shields.io/travis/manoelcampos/papers-review/master.svg)](https://travis-ci.org/manoelcampos/papers-review) [![GPL licensed](https://img.shields.io/badge/license-GPL-blue.svg)](http://www.gnu.org/licenses/gpl-3.0)


A Java 8 web application for research papers cataloging and systematic review management.

The application enables importing papers from bibtex files to manage all their review flow and metadata collection.
It also allows the generation of reports in different formats such as HTML and LaTeX.

It's an MVC application built in the server side with [VRaptor 4 web framework](http://vraptor.org), JPA 2.1 with Hibernate 4
and [Bootstrap](http://getbootstrap.com) in the client side.

# Configuration

The application was tested only in the [Wildfly 8.1](http://wildfly.org) Application Server (AS).
An data source with the database connection configuration should be created in the AS
using the exact same name defined inside the `<non-jta-data-source>` tag of the [persistence.xml](src/main/resources/META-INF/persistence.xml) file.
You can use any JPA supported database. Since the persistence.xml is configure to auto-create 
the schema, you only need to create an empty database before creating the data source.

The WildFly web console usually is accessed at [http://localhost:9990](http://localhost:9990).