'use client'; 
import { ListData } from '../doubleList'

const DoubleListItem = ({ label, content }: ListData) => {
	return (
		<div className='flex py-6 text-xl  gap-4 items-center'>
			<div className='min-w[100px] max-w-[70%] w-full font-semibold'>
				{label}
			</div>
			<div className='min-w[100px] max-w-[50%] w-full'>{content}</div>
		</div>
	)
}
export default DoubleListItem