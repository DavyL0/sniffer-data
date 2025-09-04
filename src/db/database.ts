import { MongoClient, Db, Collection } from "mongodb";

const uri = process.env.MONGO_ACESS || "";
const dbName = "logsdb";

let client: MongoClient;
let db: Db;

export async function connectDB(): Promise<void> {
  try {
    client = new MongoClient(uri);
    await client.connect();
    db = client.db(dbName);
    console.log("✅ Conectado ao MongoDB (driver oficial)");
  } catch (error) {
    console.error("❌ Erro ao conectar no MongoDB:", error);
    process.exit(1);
  }
}

export function getCollection<T>(name: string): Collection<T> {
  if (!db) {
    throw new Error("Banco de dados não inicializado. Chame connectDB primeiro.");
  }
  return db.collection<T>(name);
}
