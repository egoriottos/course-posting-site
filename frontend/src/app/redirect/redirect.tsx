'use client'
import { Card } from '@/components/ui/card'
import { getTokens } from '@/utils/authorisationLogic'
import { useEffect } from 'react'

export default function Redirect() {
	useEffect(() => {
		getTokens()
	}, [])
	return <Card>Loading...</Card>
}