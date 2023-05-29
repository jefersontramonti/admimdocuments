import React, {useEffect, useState} from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import { createUserApi } from '../../services/apiService'
import { AxiosError } from 'axios';
import AlertComponent from '../../components/AlertComponent/AlertComponent';


interface User {
    id?: number;
    name: string;
    username: string;
    password: string;
    userType: string;
}

interface UserType {
    value: string;
    label: string;
}

const userTypes: UserType[] = [
    {value: '', label: 'Selecione o tipo de usuário'},
    {value: 'employee', label: 'Funcionário'},
    {value: 'coordinator', label: 'Coordenador'},
    {value: 'document_editor', label: 'Editor de Documentos'},
    {value: 'document_approver', label: 'Aprovador de Documentos'},
];


const UserForm: React.FC = () => {
    const [user, setUser] = useState<User>({
        name: '',
        username: '',
        password: '',
        userType: '',
    });

    useEffect(() => {
        setUser({...user});
    }, []);

    const [alert, setAlert] = useState<{message: string | null, severity: 'error' | 'warning' | 'info' | 'success' | null}>({message: null, severity: null});
    const [isLoading, setIsLoading] = useState(false);

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        let newUser = {...user, [event.target.name]: event.target.value};

        if (event.target.name === "userType") {
            newUser.password = event.target.value === "employee" ? "1234" : "";
            newUser.username = event.target.value === "employee" ? "galvanotek" : "";
        }
        setUser(newUser);
    };



    const showAlertMessage = (message: string, severity: 'error' | 'warning' | 'info' | 'success') => {
        setAlert({message, severity});

        setTimeout(() => {
            setAlert({message: null, severity: null});
        }, 2000);
    };

    const createUser = async () => {
        setIsLoading(true); // Iniciar o carregamento
        try {
            await createUserApi(user);
            setUser({name: '', username: '', password: '', userType: ''});
            showAlertMessage('Usuário cadastrado com sucesso!!!', 'success');
        } catch (error) {
            console.error(error);
            let errorMessage = 'Erro ao cadastrar usuário';

            if (error instanceof AxiosError) {
                // O erro é uma instância de AxiosError
                if (error.response) {
                    // O servidor retornou uma resposta de erro
                    errorMessage += `: ${error.response.data.error}`;
                } else {
                    // Algum outro erro ocorreu (por exemplo, a rede falhou)
                    errorMessage += `: ${error.message}`;
                }
            }
            showAlertMessage(errorMessage, 'error');
        } finally {
            setIsLoading(false); // Finalizar o carregamento
        }
    };


    const handleFormSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        try {
            await createUser();
        } catch (error) {
            console.error(error);
            showAlertMessage('Erro ao cadastrar usuário', 'error');
        }
    };

    return (
        <Box maxWidth="600px" mx="auto" p={2}>
            <div>
                <h2>Cadastrar Usuário</h2>
                <form onSubmit={handleFormSubmit}>
                    <div style={{marginBottom: '10px'}}>
                        <input
                            type="text"
                            name="name"
                            value={user.name}
                            onChange={handleInputChange}
                            placeholder="Nome"
                            required
                            style={{width: '100%', padding: '8px'}}
                        />
                    </div>
                    <div style={{marginBottom: '10px'}}>
                        <select
                            name="userType"
                            value={user.userType}
                            onChange={handleInputChange}
                            required
                            style={{width: '103%', padding: '8px'}}
                        >
                            {userTypes.map((type) => (
                                <option key={type.value} value={type.value}>
                                    {type.label}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div style={{marginBottom: '10px'}}>
                        <input
                            type="text"
                            name="username"
                            value={user.userType === "employee" ? "galvanotek" : user.username}
                            onChange={handleInputChange}
                            placeholder="Nome de usuário"
                            disabled={user.userType === "employee"}
                            required
                            style={{width: '100%', padding: '8px'}}
                        />
                    </div>
                    <div style={{marginBottom: '10px'}}>
                        <input
                            type="password"
                            name="password"
                            value={user.userType === "employee" ? "1234" : user.password}
                            onChange={handleInputChange}
                            placeholder="Senha"
                            disabled={user.userType === "employee"}
                            required
                            style={{width: '100%', padding: '8px'}}
                        />
                    </div>
                    <div style={{display: 'flex', justifyContent: 'center'}}>
                        <Button variant="contained" type="submit" disabled={isLoading}>
                            Salvar
                        </Button>
                    </div>
                </form>
            </div>

            {alert.message && alert.severity && (
                <AlertComponent message={alert.message} severity={alert.severity} />
            )}
        </Box>

    );
};

export default UserForm;


