package pl.piomin.services.aws.order.add;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import pl.piomin.services.aws.order.model.Order;

public class PostOrder implements RequestHandler<Order, Order> {

	private DynamoDBMapper mapper;
	
	public PostOrder() {
		AmazonDynamoDBClient client = new AmazonDynamoDBClient();
		mapper = new DynamoDBMapper(client);
	}
	
	@Override
	public Order handleRequest(Order o, Context ctx) {
		LambdaLogger logger = ctx.getLogger();
		mapper.save(o);		
		Order r = o;
		logger.log("Order saved: " + r.getId());
		return r;
	}
	
}