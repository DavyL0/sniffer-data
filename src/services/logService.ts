import mongoose, { Schema, Document } from "mongoose";

export interface Log extends Document {
  source: string;
  message: string;
  createdAt: Date;
}

const LogSchema: Schema = new Schema({
  source: { type: String, required: true },
  message: { type: String, required: true },
  createdAt: { type: Date, default: Date.now }
});

const LogModel = mongoose.model<Log>("Log", LogSchema);

class LogRepository {
  async saveLog(source: string, message: string): Promise<Log> {
    const log = new LogModel({ source, message });
    return await log.save();
  }

  async getAllLogs(): Promise<Log[]> {
    return await LogModel.find().sort({ createdAt: -1 }).exec();
  }
}

export default new LogRepository();
