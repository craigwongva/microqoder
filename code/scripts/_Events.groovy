eventCreateWarStart = { warName, stagingDir ->

    def s1 = 
      "git show -s".execute() | 
      "grep commit".execute()
    def t1 = s1.text.split()
    def t2 = t1[1]
    println s1
    println t1
    println t2

    ant.manifest(file: "${stagingDir}/META-INF/MANIFEST.MF", mode: "update") {
        attribute(name: "Build-Time", value: new Date())        
        section(name: "Grails Application") {       
            attribute(name: "Implementation-Build-Number", value: t2 )
        }
    }
}
