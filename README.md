# MicroService: Resturcturing my Job Application from Monolithic architecture to MicroService.

![MicroService](image.png)

## ``` How I am going to structure my three microservice ```
![Port](image-1.png)

    I have 3 different microservice running on 3 different ports, We can see in the above image.

    These 3 microservices are going to 3 different springboot projects.

``` Why so? ```

    we are going to break down a single application which is in
    monolithic architecture into 3 different parts and they will
    be responsible for running as a single application. So for 
    the end user it will be like they are interacting a single
    application but in backend I have 3 different microservices
    running on 3 different ports

### ``` New review urls for microservice ```

![review urls](image-2.png)

    In my monolithic architecture my review urls is:- 
    
    [ GET /companies/{companyId}/reviews ] --> this url use path parameter
    mean's within the url path I had the companyId parameter.

    But in my microservice review urls are different:-

    Here I used Query parameter mean's passing the parameter in the form of
    query.
``` Query Parameter: It is a optional parameters in a URL that modify the behaviour of request ```

    The reason behind resturcturing the urls is my microservices less to know about each other, need to be independent.

    