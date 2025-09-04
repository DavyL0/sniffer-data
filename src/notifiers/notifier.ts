class Notifier {
  alert(message: string): void {
    // Aqui poderia ser substituído por envio de email, Slack, etc.
    console.log(message);
  }
}

export default new Notifier();
