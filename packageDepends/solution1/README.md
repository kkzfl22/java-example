###采用提取中介者的主式，解决循环依赖的问题

##中心思想，就是把两个相互依赖的组件的中的交互部分，抽象出来形成一个新的组件,而新的组件同时包含着原有两个组件的引用,这样就把循环依赖剥离出来并提取到一个专门的中介者组件中