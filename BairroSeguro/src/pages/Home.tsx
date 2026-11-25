export type RotaInterna = "home" | "morador" | "ocorrencia" | "redeDeApoio" | "contatoDeEmergencia" ;

interface HomeProps {
  onNavigate: (rota: RotaInterna) => void;
}

export function Home({ onNavigate }: HomeProps) {
  return (
    <main style={{ maxWidth: 520, margin: "2rem auto", padding: "0 1rem" }}>
      <h1>Bairro Seguro — Home</h1>
      <p>Escolha para onde deseja ir:</p>

      <div style={{ display: "flex", gap: "0.75rem", marginTop: "1rem" }}>
        <button type="button" onClick={() => onNavigate("morador")}>
          Moradores
        </button>

        <button type="button" onClick={() => onNavigate("ocorrencia")}>
          Ocorrências
        </button>

        <button type="button" onClick={() => onNavigate("redeDeApoio")}>
          Redes de apoio
        </button>

        <button type="button" onClick={() => onNavigate("contatoDeEmergencia")}>
          Contatos de Emergência
        </button>
      </div>
    </main>
  );
}