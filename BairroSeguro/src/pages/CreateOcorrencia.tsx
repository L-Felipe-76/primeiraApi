import { useState} from "react";
import type { FormEvent} from "react";

export interface RegisterPayload {
  tituloOcorrencia: string;
  descricao: string;
  tag: "Criminal" | "Infraestrutural" | "Ajuda_especializada" | string;
};

const formInicial: RegisterPayload = {
  tituloOcorrencia: "",
  descricao: "",
  tag: "Criminal"
};

export function CreateOcorrenciaFunc() {
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
      const response = await fetch("http://localhost:8081/bairroSeguro/ocorrencias", {
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

      setSucess("Ocorrencia relatada com sucesso!");
      setFormData(formInicial);
      return resultado;
      
    } catch (err) {
        if (err instanceof Error) {
        setErro(err.message);
        } else {setErro("Não foi possível relatar a ocorrencia.");}

    } finally {
        setSending(false);
    }
  }

  return (
    <main style={{ maxWidth: 520, margin: "2rem auto", padding: "0 1rem" }}>
      <h1>Relatar ocorrencia</h1>

      <form onSubmit={handleSubmit} style={{ display: "grid", gap: "0.75rem" }}>
        <label>
          Titulo da ocorrência
          <input
            type="text"
            name="tituloOcorrencia"
            value={formData.tituloOcorrencia}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Descrição da ocorrência
          <input
            type="text"
            name="descricao"
            value={formData.descricao}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Tag da ocorrência
          <select name="tag" value={formData.tag} onChange={handleChange}>
            <option value="Criminal">Criminal</option>
            <option value="Infraestrutural">Infraestrutural</option>
            <option value="Ajuda_especializada">Ajuda especializada</option>
          </select>
        </label>

        <button type="submit" disabled={sending}>
          {sending ? "Enviando..." : "Relatar ocorrência"}
        </button>
      </form>

      {sucess && <p style={{ color: "green", marginTop: "1rem" }}>{sucess}</p>}
      {erro && <p style={{ color: "red", marginTop: "1rem" }}>Erro: {erro}</p>}
    </main>
  );
}