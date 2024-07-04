'use client'
import axios from 'axios'
import jwt_decode from 'jwt-decode'
import queryString from 'query-string'
import { TOKEN_URL } from './authorisationLogic'
import { UserRole } from '@/types/user-role'

export type TokenType = {
	access_token: string
	refresh_token: string
}
export const isTokenInLS = (): boolean => {
	const token = window.localStorage.getItem('token')
	return token !== null && token !== undefined
}

export const saveTokenInLS = (token: TokenType) => {
	const convertedToken = JSON.stringify(token)
	window.localStorage.removeItem('token')
	window.localStorage.setItem('token', convertedToken)
}
export const checkIsTokenExpired = (token: string): boolean => {
	const decodedToken: any = jwt_decode(token)
	const now = new Date().getTime()
	const expirationDate = decodedToken.exp * 1000
	return now > expirationDate
}

export const updateToken = async () => {
	const token = localStorage.getItem('token')

	if (token) {
		const refreshToken = JSON.parse(token).refresh_token

		const body = {
			client_id: 'user-service',
			grant_type: 'refresh_token',
			refresh_token: refreshToken,
		}
		console.log('Пытаюсь обновить токены')

		await axios
			.post<TokenType>(TOKEN_URL!, queryString.stringify(body), {
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded',
				},
			})
			.then(data => {
				const tokens: TokenType = {
					access_token: data.data.access_token,
					refresh_token: data.data.refresh_token,
				}
				saveTokenInLS(tokens)
			})
			.catch(error => {
				console.error(error)
				if (error.response.status === 400) {
					window.localStorage.removeItem('token')
					// window.location.replace(window.location.origin);
				}
			})
	}
}

export type JwtPayloadType = {
	exp: string
	preferred_username: string
	resource_access: {
		'user-service': {
			roles: string[]
		}
	}
}

export const getTokens = (): TokenType => {
	return isTokenInLS() ? JSON.parse(localStorage.getItem('token')!) : {}
}

export const getDecodedToken = (): JwtPayloadType => {
	// @ts-ignore
	return jwt_decode(getTokens().access_token)
}

export const getOrCreateTokens = (): TokenType | null => {
	const tokens = getTokens()
	return tokens ? tokens : null
}
export function getUserRole(): UserRole | undefined {
    if (isTokenInLS()) {
        const decodedToken = getDecodedToken()
        const roles = decodedToken?.resource_access?.['user-service']?.roles

        if (roles?.includes('client-student')) return 'student'
        if (roles?.includes('client-teacher')) return 'teacher'
        if (roles?.includes('client-admin')) return 'admin'
    }
}
export function signOut() {
	if (typeof window !== 'undefined') {
		let baseUrl
		if (window.location.host == 'localhost:3000') {
			baseUrl = 'dppmai.ru'
		} else {
			baseUrl = window.location.host
		}
		window.localStorage.removeItem('token')
		window.sessionStorage.removeItem('code_verifier')
		window.sessionStorage.removeItem('state')

		let id_token = window.localStorage.getItem('id_token')
		const domain =
			process.env.REACT_APP_KEYCLOACK_LOGOUT_URL ||
			`https://auth.${baseUrl}/realms/group-2/protocol/openid-connect/logout`
		const KEYCLOACK_LOGOUT_URL = `${domain}?id_token_hint=${id_token}&post_logout_redirect_uri=${window.location.origin}/`

		window.location.assign(KEYCLOACK_LOGOUT_URL)
	}
}