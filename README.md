# AndroidDagger
This application is a Dagger2 based application. 

# #Technology 

* * Retrofit
* * Dagger2
* * RXJava
* * MVVM architecture

# Benefits of Dagger2
* It allows the developer do register dependencies on a single entry point for each component
* It sounds great the whole "no reflection" business"
* Since dependencies can be injected and configured externally, we can reuse those components.
* When injecting abstractions as collaborators, we can just change the implementation of any object without having to make a lot of changes in our codebase, since that object instantiation resides in one place isolated and decoupled.
* Dependencies can be injected into a component: it is possible to inject mock implementations of these dependencies which makes testing easier.


![image_2019_06_18T08_29_32_780Z](https://user-images.githubusercontent.com/4899907/59665942-b27b5700-91d5-11e9-96d2-836c96a31f67.png)


* *ViewModel is injected using a concept named Multi Binding.
* *@Singleton is used to application level dependency, it will stay as long a application exists, and it should be static when it is possible

* *@Provides and @Binds annotations are same, when we have a method body we use @Binds andwhen the method body is missing the we use @Provides
