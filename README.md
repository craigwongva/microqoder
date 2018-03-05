This works in the `master` branch:
```
aws cloudformation create-stack --stack-name simple-stack-qoder \
   --template-body file://simple-pipeline-qoder.yml 
   --region us-west-2 \
   --capabilities CAPABILITY_NAMED_IAM 
   --parameter ParameterKey=githubpassword,ParameterValue=REDACTED
```
