// apiService.ts
import axios from 'axios';

export const getUsers = async () => {
    return await axios.get('/api/users');
}

export const getUserById = async (id: string) => {
    return await axios.get(`/api/users/${id}`);
}

export const updateUser = async (id: string, user: any) => {
    return await axios.put(`/api/users/${id}`, user);
}

export const createUserApi = async (user: any) => {
    return await axios.post('/api/users', user);
}

export const deleteUserById = async (id: string) => {
    return await axios.delete(`/api/users/${id}`);
}

