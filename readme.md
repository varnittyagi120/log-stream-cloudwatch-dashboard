This POC is to store application logs to AWS cloudwatch and
populate dashboard for monitoring on the top of cloudwatch 
log stream 
Steps to achieve this POC :
1. create a springboot microservice in local, and expose a rest
api to log success and failure scenarios
2. Run it in local and verify logs are being printed at 
external location in a separate file 
3. Create an ec2 instance and defined inbound rules for ec2
4. Take Ec2 ssh in local 
5. copy local application.jar file to EC2 using below sample command 
Copy command :
scp -i /home/admin-v/Downloads/cloudwatch-demo-poc.pem /home/admin-v/Downloads/demo-project/demo/target/demo-0.0.1-SNAPSHOT.jar ubuntu@ec2-54-185-233-12.us-west-2.compute.amazonaws.com:~
6.  install java version on ec2
7. run java -jar application.jar on ec2 
8. make sure that your application is running on 8080
on ec2 
9. Using DNS Ipv4 public address, hit api exposed in ec2 
   and verify logs are being printed on ec2 or not 
10. To push application logs we have to install cloudwatch
agent
11. Install python on ec2, version between(2.7 to 3.5) is 
accepted 
12. Follow below blog post to push logs to cloudwatch 
https://akkireddy.medium.com/how-to-setup-and-push-application-logs-to-aws-cloudwatch-354a28d5863
13. Create an IAM role for Cloudwatch
AWS IAM -> Policy -> Create Policy
select JSON
configure below json :
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "logs:CreateLogGroup",
        "logs:CreateLogStream",
        "logs:PutLogEvents",
        "logs:DescribeLogStreams"
    ],
      "Resource": [
        "arn:aws:logs:*:*:*"
    ]
  }
 ]
}

14. create IAM role with above policy and attach it to 
     ec2
15. Download cloudwatch agent script using below command on ec2
curl https://s3.amazonaws.com/aws-cloudwatch/downloads/latest/awslogs-agent-setup.py -O
16. Create log stream 
17. Use the log stream while installing cloudwatch agent 
18. command to run python script to install cloudwatch agent 
sudo python ./awslogs-agent-setup.py --region us-west-1
19. Manage the logs agent service using the following command :
sudo service awslogs start
sudo service awslogs stop
sudo service awslogs restart
20. Hit the api running on ec2 and verify logs are coming to
log stream or not 
21. create dashboard using cloudwatch console 
22. create a widget to show success, failure and total 
number of transactions using bar graph 
23. create bar graph using log insights 

