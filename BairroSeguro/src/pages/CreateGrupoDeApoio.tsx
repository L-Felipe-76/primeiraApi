import { useState} from "react";
import type { FormEvent} from "react";

export interface RegisterPayload {
  cep: string;
  estado: string;
  cidade: string;
  bairro: string;
  integrantes: number;
};

const formInicial: RegisterPayload = {
  cep: "",
  estado: "",
  cidade: "",
  bairro: "",
  integrantes: 0
};

export function CreateGrupoDeApoioFunc() {
  const [formData, setFormData] = useState<RegisterPayload>(formInicial);
  const [sending, setSending] = useState(false);
  const [erro, setErro] = useState<string | null>(null);
  const [sucess, setSucess] = useState<string | null>(null);

  function handleChange(e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  }

  async function handleSubmit(e: FormEvent<HTMLFormElement>) {
    e.preventDefault();
    setSending(true);
    setErro(null);
    setSucess(null);

    try {
      const response = await fetch("http://localhost:8081/bairroSeguro/redesDeApoio", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        const contentType = response.headers.get("content-type") || "";
        if (contentType.includes("application/json")) {
          const errorBody = await response.json().catch(() => null);
          throw new Error(errorBody.message || `Erro HTTP ${response.status}`);
        }
        const errorText = await response.text().catch(() => "");
        throw new Error(errorText || `Erro HTTP ${response.status}`);
      }

      const contentType = response.headers.get("content-type") || "";
      const resultado = contentType.includes("application/json")
        ? await response.json().catch(() => null)
        : await response.text().catch(() => null);

      setSucess("Rede de apoio criada com sucesso!");
      setFormData(formInicial);
      return resultado;
      
    } catch (err) {
        if (err instanceof Error) {
        setErro(err.message);
        } else {setErro("Não foi possível criar a rede de apoio.");}

    } finally {
        setSending(false);
    }
  }

  return (
    <main style={{ maxWidth: 520, margin: "2rem auto", padding: "0 1rem" }}>
      <h1>Adicionar Morador</h1>

      <form onSubmit={handleSubmit} style={{ display: "grid", gap: "0.75rem" }}>
        <label>
          Cep da rede de apoio
          <input
            type="text"
            name="cep"
            value={formData.cep}
            onChange={handleChange}
            required
          />
        </label>
        
        <label>
          Estado da rede de apoio
          <input
            type="text"
            name="estado"
            value={formData.estado}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Cidade da rede de apoio
          <input
            type="text"
            name="cidade"
            value={formData.cidade}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Bairro da rede de apoio
          <input
            type="text"
            name="bairro"
            value={formData.bairro}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Quantidade de integrantes da rede de apoio
          <input
            type="number"
            name="integrantes"
            value={formData.integrantes}
            onChange={handleChange}
            required
          />
        </label>

        <button type="submit" disabled={sending}>
          {sending ? "Enviando..." : "Criar rede de apoio"}
        </button>
      </form>

      {sucess && <p style={{ color: "green", marginTop: "1rem" }}>{sucess}</p>}
      {erro && <p style={{ color: "red", marginTop: "1rem" }}>Erro: {erro}</p>}
    </main>
  );
}