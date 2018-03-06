import sys
import boto3

BUCKET = sys.argv[1] #'sample-wordpress-pipeline-artifactstorebucket-1olq1vs44unkb'
#BUCKET = 'sample-wordpress-pipeline-artifactstorebucket-juuogfhluunc'

s3 = boto3.resource('s3')
bucket = s3.Bucket(BUCKET)
bucket.object_versions.delete()

