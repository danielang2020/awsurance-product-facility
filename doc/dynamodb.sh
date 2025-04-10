docker run -d --publish 4566:8000 amazon/dynamodb-local:2.6.0 -jar DynamoDBLocal.jar -inMemory -sharedDb

aws dynamodb list-tables --endpoint-url http://localhost:4566

aws dynamodb create-table --table-name InsuranceProduct \
                          --key-schema AttributeName=insuranceTenant,KeyType=HASH AttributeName=productCode,KeyType=RANGE \
                          --attribute-definitions AttributeName=insuranceTenant,AttributeType=S AttributeName=productCode,AttributeType=S \
                          --endpoint-url http://localhost:4566 \
                          --billing-mode PAY_PER_REQUEST \
                          --region us-west-2

aws dynamodb query \
    --table-name InsuranceProduct \
    --key-condition-expression "insuranceTenant = :insuranceTenant" \
    --expression-attribute-values '{":insuranceTenant":{"S":"reprehenderit dolore cupidatat"}}' \
    --endpoint-url http://localhost:4566


aws dynamodb create-table --table-name InsuranceRateTable \
                          --key-schema AttributeName=insuranceTenant,KeyType=HASH AttributeName=rateTableCode,KeyType=RANGE \
                          --attribute-definitions AttributeName=insuranceTenant,AttributeType=S AttributeName=rateTableCode,AttributeType=S \
                          --endpoint-url http://localhost:4566 \
                          --billing-mode PAY_PER_REQUEST \
                          --region us-west-2