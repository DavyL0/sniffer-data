const connectToDatabase = require("../db/database");

class LogRepository {
  async saveLog(source, message) {
    const db = await connectToDatabase();
    const logsCollection = db.collection("logs");

    const logDocument = {
      source,
      message,
      created_at: new Date(),
    };

    const result = await logsCollection.insertOne(logDocument);
    return {
      _id: result.insertedId,
      source,
      message,
      created_at: logDocument.created_at,
    };
  }

  async getAllLogs() {
    const db = await connectToDatabase();
    const logsCollection = db.collection("logs");
    
    const logs = await logsCollection.find({}).sort({ created_at: -1 }).toArray();

    return logs;
  }
}

module.exports = new LogRepository();