# microqoder

## Motivation

## Screenshots

## Technology/Framework Used

## Features

## Installation
```
aws cloudformation create-stack --stack-name master \
   --template-body file://cf.yml 
   --region us-west-2 \
   --capabilities CAPABILITY_NAMED_IAM 
   --parameter ParameterKey=githubpassword,ParameterValue=REDACTED
```

## Tests
```
Without any special testing action, look in here for your new EC2 instances (Stage, Prod) files:
https://us-west-2.console.aws.amazon.com/cloudwatch/home?region=us-west-2#logStream:group=oveja

Near the bottom of the file should be:

candlestick microcero ae60e50aa53aaa70de799a67f9a38493d05c735b i-0fcd1545bd50ce8b6 2018-03-10:23:44:27.920
```
## Usage
http://34.211.226.123:8080/Qoder-0.1/, but actually you don't need to visit the URL.

## Teardown
```
sudo pip install boto3

python utilities/delete-versioned-bucket.py \
   `aws cloudformation list-exports --query "Exports[?Name=='microqoder-bucket'].Value" --no-paginate --output text`

aws cloudformation delete-stack --stack-name master --region us-west-2
```

## Credits

``````

```
--
scripts/_Events.groovy 

    places the sha into META/MANIFEST.MF

conf/BootStrap.groovy 

    uses the sha, i.e. println sha, which appears in the Tomcat log
--
### Friday 2/24/18:

read this more thoroughly:
https://docs.aws.amazon.com/AmazonCloudWatch/latest/logs/QuickStartEC2Instance.html

can you install via wizard using streamed lines with >> operator?

maybe remove my s3://redf4rth-microcero/france.zip if it's not effective

### Sunday 2/26/18...:

microcero pipeline needs to use a simpler structure:
  source-microcero
  build-microcero (currently it's a Test stage)
  deploy-microcero

(currently it's complicated, because it's a descendant of basic-pipeline,
which does source-saynext,source-geoserver,source-gocontainer,source-tegola/
build,build,build,build/validate)

A lot of the commented-out code in microcero-pipeline could be deleted I THINK
because that code is stored permanently in basic-pipeline.yml.

We launch microcero-pipeline, yet its pipeline name becomes wordpress-pipeline.
That's confusing.

--
next: merge microcero into microqoder
so that committing to microqoder should cause microqoder's commit sha to change
then deprecate microcero (like today we deprecated microcero-pipeline.yml from
wordpress-build-test).

These need to move from microcero to microqoder:
craigspec.yml
cf-ec2-get-artifact.{yml,json}

M4 definition inside wordpress/simple-pipeline.yml

someday:
change cf-ec2-get-artifact.yml to use microqoder/install-awslogs and install-tomcat, but

### Sunday 3/4/18 going to sleep

Written in my iPhone:
  Identify each S3 bucket (pipeline, cb, cd, cf). Use only hardcoded S3 buckets.
  Which gets CodeBuild output?
  Zip the CB output and maybe ignore the Artifacts section.
    See https://stackoverflow.com/questions/44853819/issue-creating-zip-file-through-aws-codebuild-for-nodejs-lambda

### Monday 3/5/18

S3 used in cf-CD-not-CF.yml:

  ArtifactStoreBucket
    owned by overall CloudFormation
  M4DailyArtifact
    lives inside M4CodeBuildProject
    I'm inferring this is the build output location
    because input location is GitHub repo 'microqoder'
    !Ref ArtifactStoreBucket
  ArtifactStore
    lives inside fridaypipeline (aka OklahomaPipeline)
    hardcoded as 'codepipeline-us-west-2-510060297905'
      confirmed: this where craigspeca and bagartifact content lives
      because CraigspecArtifact and BagArtifact are defined dynamically
        ..in this fridaypipeline
      Inside bagartifact is a single file called uv1Bnbe. When I download this
        ..it appears as a zip. When I unzip it, it contains Qoder-0.1.war.
        ..So I just need to get appspec.yml into this zip.

S3 used in craigspec.yml
  artifacts
    Since the CodeBuild occurs inside fridaypipeline and declares
      ..BagArtifact as its output artifact, 
      
My laptop's Microsoft Edge can't look at S3. But Chrome can. Wait, it looks
like you can see S3 in Edge if you click from another console page (I forget
which).

Why doesn't CodeBuild see my changes in craigspec.yml? I'm now having to force
it by changing the yml name.

Apparently the CloudFormation yml for CodeBuild can only read from
branch 'master'! So my changes in branch CD-not-CF are never seen :(

I guess I'll have to use minutes.doc 8/11/16's "how to restore a repo to
a known good state" and always work in 'master'.

Maybe this article explains why M4 always can't find craig2spec, even
when running from master:
https://aws.amazon.com/blogs/devops/how-to-enable-caching-for-aws-codebuild/

What exactly is read from the cache? How do you turn off CodeBuild caching? 

[Container] 2018/03/06 00:58:53 Waiting for agent ping
[Container] 2018/03/06 00:58:53 Waiting for DOWNLOAD_SOURCE

This seemed to have been solved by kicking the TV, i.e. this was solved by
switching from craig2spec.yml to judyspec.yml. I don't know why this helped.

Container] 2018/03/06 02:58:41 Expanding foobar
[Container] 2018/03/06 02:58:41 Skipping invalid artifact path foobar
[Container] 2018/03/06 02:58:41 Phase complete: UPLOAD_ARTIFACTS Success: false
[Container] 2018/03/06 02:58:41 Phase context status code: CLIENT_ERROR Message: No matching artifact paths found
[Container] 2018/03/06 02:58:44 Runtime error (*errors.errorString: No matching artifact paths found)

This occurred because I build3spec.yml was outputting target/Qoder-0.1.war
but the proper path should be microcero/target/Qoder-0.1.war.

--Tuesday 3/6/18--

If I open cloud9 in us-east-1 by accident, then click to us-west-2, I can't see
my existing IDE. 

So close the browser, re-login, but go directly to us-west-2 somehow, then
to Cloud9.

Oh, so sioYGnp conatins build-output.zip which contains Qoder-0.1.war and 
appspec.yml.

No such file or directory - /opt/codedeploy-agent/deployment-root/0017c249-fce8-4d4f-9034-455c5637f473/d-B4SQEOIQR/deployment-archive/Roder-0.1.war

why Roder? oh, appspec.yml is outdated.

CodeDeploy is succeeding, but the war seems wrong in Tomcat. That is, I looked
in the S3 bucket content and see a good Qoder-1.0.war, and download to my Desktop
and upload to S3 and download to my EC2 instance, I put it in /webapps, and it
expands properly with a candlestick tracer and is accessible over the web.

In contrast, the CD-copied war into /webapps is way too small, like it's
not a real war.

OK, solved the above by sending to /webapps not /webapps/Toder, which creates
a Toder dir and hides the war inside that dir, where Tomcat doesn't see it.

2018-03-06 17:01:25,133 - cwlogs.push.publisher - WARNING - 8329 - Thread-3 -
Caught exception: An error occurred (AccessDeniedException) when 
calling the PutLogEvents operation: 
User: arn:aws:sts::994238729631:
assumed-role/CD2InstanceRole/i-0576debd2e3287fa4 
is not authorized to perform: 
logs:PutLogEvents on resource: 
arn:aws:logs:us-west-2:994238729631:log-group:oveja:log-stream:i-0576debd2e3287fa4

next: downscope CD2InstanceRole, which I just gave CloudWatchLogsFullAccess
next: copy groovy/hello.groovy CloudWatch AWS CLI code

force commit
