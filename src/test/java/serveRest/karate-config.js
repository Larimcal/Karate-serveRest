function fn(){
    var env = karate.env; // get system property 'karate.env'
    var config = { env: env }
    var headers = { 'cache-control': 'no-cache', 'content-type': 'application/json' }
    var domain = karate.read('classpath:serveRest/domain/config.yaml')

    karate.log('karate.env system property was:', env);
    karate configure('headers', headers);
    karate.configure('retry', { count: 3, interval: 2000 });
    return config;
}