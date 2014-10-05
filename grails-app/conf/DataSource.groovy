hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
    singleSession = true
}
dataSource{
    pooled = true
    driverClassName = "org.postgresql.Driver"
    username = "test"
    password = "test"
}
// environment specific settings
environments {
    development {
        dataSource {
            url = "jdbc:h2:mem:devDB" //or any other path
            driverClassName = "org.h2.Driver"
            username = "sa"
            password = ""
            dialect = ""
            dbCreate = "create-drop"
        }
    }
    test {
        dataSource {
            dbCreate = "create-drop"
            dialect = org.hibernate.dialect.PostgreSQLDialect
            url = "jdbc:postgresql://localhost/test"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            driverClassName = "org.postgresql.Driver"
            dialect = org.hibernate.dialect.PostgreSQLDialect

            uri = new URI(System.env.DATABASE_URL?:"postgres://test:test@localhost/test")

            url = "jdbc:postgresql://"+uri.host+uri.path
            username = uri.userInfo.split(":")[0]
            password = uri.userInfo.split(":")[1]
        }
    }
}
