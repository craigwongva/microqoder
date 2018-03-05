Friday 2/24/18:

read this more thoroughly:
https://docs.aws.amazon.com/AmazonCloudWatch/latest/logs/QuickStartEC2Instance.html

can you install via wizard using streamed lines with >> operator?

maybe remove my s3://redf4rth-microcero/france.zip if it's not effective

Sunday 2/26/18...:

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