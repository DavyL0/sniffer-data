import logRepository, { Log } from "../repositories/logRepository";

class LogService {
  async createLog(source: string, message: string): Promise<Log> {
    if (!source || !message) {
      throw new Error("Campos 'source' e 'message' são obrigatórios");
    }
    return await logRepository.saveLog(source, message);
  }

  async listLogs(): Promise<Log[]> {
    return await logRepository.getAllLogs();
  }
}

export default new LogService();
