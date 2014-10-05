dataSource {
    url = ""
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
    dialect = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:file:~/.h2" //or any other path
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:file:~/.h2" //or any other path
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
