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
      
My laptop's Microsoft Edge can't look at S3. But Chrome can.      