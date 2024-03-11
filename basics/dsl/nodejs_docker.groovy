job('NodeJS Docker example') {
    scm {
        git('https://github.com/RazAsisjlm/ci-cd.git','*/main') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL NodeJs User')
            node / gitConfigEmail('jenins-dsl@domain.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
   
    
    steps {
        dockerBuildAndPublish {
            repositoryName('RazAsisjlm/jenkins-lab')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            buildContext('./basics/')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

