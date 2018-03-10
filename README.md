This works in the `master` branch:
```
aws cloudformation create-stack --stack-name master \
   --template-body file://cf-master.yml 
   --region us-west-2 \
   --capabilities CAPABILITY_NAMED_IAM 
   --parameter ParameterKey=githubpassword,ParameterValue=REDACTED

python delete-versioned-bucket.py \
   `aws cloudformation list-exports --query "Exports[?Name=='microqoder-bucket'].Value" --no-paginate --output text`

aws cloudformation delete-stack --stack-name master --region us-west-2
