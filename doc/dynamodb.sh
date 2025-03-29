docker run -d --publish 4566:8000 amazon/dynamodb-local:1.19.0 -jar DynamoDBLocal.jar -inMemory -sharedDb

aws dynamodb create-table --table-name InsuranceProduct \
                          --key-schema AttributeName=tenant,KeyType=HASH AttributeName=code,KeyType=RANGE \
                          --attribute-definitions AttributeName=tenant,AttributeType=S AttributeName=code,AttributeType=S \
                          --endpoint-url http://localhost:4566 \
                          --billing-mode PAY_PER_REQUEST

aws dynamodb list-tables --endpoint-url http://localhost:4566

aws dynamodb query \
    --table-name InsuranceProduct \
    --key-condition-expression "tenant = :tenant" \
    --expression-attribute-values '{":tenant":{"S":"culpa sint"}}' \
    --endpoint-url http://localhost:4566