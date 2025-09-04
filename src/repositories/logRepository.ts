import { ObjectId } from "mongodb";
import { getCollection } from "../db/database";

export interface Log {
  _id?: ObjectId;
  source: string;
  message: string;
  createdAt: Date;
}

class LogRepository {
  private collection = getCollection<Log>("logs");

  async saveLog(source: string, message: string): Promise<Log> {
    const log: Log = { source, message, createdAt: new Date() };
    const result = await this.collection.insertOne(log);
    return { ...log, _id: result.insertedId };
  }

  async getAllLogs(): Promise<Log[]> {
    return await this.collection.find().sort({ createdAt: -1 }).toArray();
  }
}

export default new LogRepository();
