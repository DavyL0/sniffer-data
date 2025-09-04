import { Router } from "express";
import logController from "../controller/logController";

const router = Router();

router.post("/webhook", (req, res) => logController.receiveWebhook(req, res));
router.get("/logs", (req, res) => logController.getLogs(req, res));

export default router;
