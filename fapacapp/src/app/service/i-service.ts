import { Observable } from "rxjs";
import { RequisicaoPaginada } from "../model/RequisicaoPaginada";
import { RespostaPaginada } from "../model/RespostaPaginada";

export interface IService<T> {
    apiUrl: string;
    get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<T>>;
    getById(id: number): Observable<T>;
    save(objeto: T): Observable<T>;
    delete(id: number): Observable<void>;
}