import fs from "fs";
import logService from "../services/logService";
import notifier from "../notifiers/notifier";

export function startWatching(logFilePath: string): void {
  console.log(`👀 Monitorando arquivo de log: ${logFilePath}`);

  fs.watchFile(logFilePath, { interval: 1000 }, async () => {
    const content = fs.readFileSync(logFilePath, "utf8");
    const lines = content.trim().split("\n");
    const lastLine = lines[lines.length - 1];

    if (lastLine) {
      try {
        await logService.createLog("AppExterna", lastLine);
        console.log(`📥 Log salvo: ${lastLine}`);

        if (lastLine.includes("ERROR") || lastLine.includes("CRITICAL")) {
          notifier.alert(`🚨 ALERTA! Log crítico detectado: ${lastLine}`);
        }
      } catch (err) {
        console.error("Erro ao salvar log:", err);
      }
    }
  });
}
