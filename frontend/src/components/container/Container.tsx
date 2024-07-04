
import { SideBar } from '@/components/side-bar/side-bar'
import { Card } from '@/components/ui/card'

export const Container = ({
    children,
}: Readonly<{
    children: React.ReactNode
}>)=>{
  return (
    <div className='flex w-full p-7 bg-white'>
        <SideBar/>
    <div className='w-full flex justify-center'>
        <Card className='w-3/4 p-4'>{children}</Card>
    </div>
</div>
  )
}

