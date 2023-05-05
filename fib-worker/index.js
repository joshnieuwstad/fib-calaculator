import { createClient } from "redis";
import { redisHost, redisPort } from "./keys.js";

const client = createClient({
  socket: {
    host: redisHost,
    port: redisPort,
  },
});

client.on("error", async (err) => {
  console.log("Redis Client Error", err);
  await client.disconnect();
});

await client.connect();

const subscriber = client.duplicate();
const publisher = client.duplicate();

await subscriber.connect();
await publisher.connect();

function fib(index) {
  if (index < 2) return 1;

  return fib(index - 1) + fib(index - 2);
}

subscriber.subscribe(
  "channel:index",
  async (message) => {
    const index = parseInt(Buffer.from(message).toString());
    const value = fib(index);
    await publisher.publish(
      "channel:value",
      JSON.stringify({
        index,
        value,
      })
    );
  },
  true
);
