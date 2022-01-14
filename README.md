aws s3api create-bucket --bucket s3demo --region eu-west-1 --endpoint-url http://127.0.0.1:4566

aws s3api delete-bucket --bucket s3demo --region eu-west-1 --endpoint-url http://127.0.0.1:4566


http://s3demo.s3.localhost.localstack.cloud:4566/

aws sqs create-queue --queue-name s3demo-sqs-queue --endpoint-url http://127.0.0.1:4566

http://localhost:4566/000000000000/s3demo-sqs-queue