import { ApolloServer } from 'apollo-server';
import { ApolloGateway } from '@apollo/gateway';

const server = new ApolloServer({
    gateway: new ApolloGateway({
        debug: true,
        serviceList: [
            { name: "department-server", url: "http://localhost:4001/graphql" },
            { name: "employee-server", url: "http://localhost:4002/graphql" }
        ]
    }),
    subscriptions: false
});

async function startup() {
    const { url } = await server.listen();
    console.log(`Apollo gateway started, please access '${url}'`);
}

startup();