const { MongoClient } = require("mongodb");


const uri = process.env.MONGO_ACESS;

const client = new MongoClient(uri);

async function connectAndInsertLog() {
  try {
    await client.connect();

    console.log("Connected successfully to server");

    const database = client.db("logs_db"); 
    const logsCollection = database.collection("logs"); 

    
    const logDocument = {
      source: "application_A",
      message: "User logged in successfully.",
      created_at: new Date() 
    };

   
    const result = await logsCollection.insertOne(logDocument);

    console.log(`A document was inserted with the _id: ${result.insertedId}`);

  } finally {
    await client.close();
  }
}

connectAndInsertLog().catch(console.dir);