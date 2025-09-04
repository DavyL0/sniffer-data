import { Request, Response } from "express";
import logService from "../services/logService";

class LogController {
  async receiveWebhook(req: Request, res: Response): Promise<void> {
    try {
      const { source, message } = req.body;
      const log = await logService.createLog(source, message);
      res.status(201).json(log);
    } catch (error: any) {
      res.status(400).json({ error: error.message });
    }
  }

  async getLogs(req: Request, res: Response): Promise<void> {
    try {
      const logs = await logService.listLogs();
      res.json(logs);
    } catch (error: any) {
      res.status(500).json({ error: error.message });
    }
  }
}

export default new LogController();
