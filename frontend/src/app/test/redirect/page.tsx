import dynamic from 'next/dynamic'

const DynamicRedirect = dynamic(() => import('@/app/redirect/redirect'), {
	ssr: false,
})
export default function Page() {
	return <DynamicRedirect />
}