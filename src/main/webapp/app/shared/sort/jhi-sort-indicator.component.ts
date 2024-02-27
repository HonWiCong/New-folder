import Component from 'vue-class-component';
import { Prop, Vue } from 'vue-property-decorator';
import { ChevronUp, ChevronDown, ChevronsUpDown } from 'lucide-vue';
import { ArrowUpDown, ArrowUp, ArrowDown } from 'lucide-vue';

@Component({
	components: {
		ChevronUp,
		ChevronDown,
		ChevronsUpDown,
		ArrowUpDown,
		ArrowUp,
		ArrowDown,
	},
})
export default class JhiSortIndicatorComponent extends Vue {
	@Prop()
	currentOrder: string;
	@Prop()
	fieldName: string;
	@Prop()
	reverse: boolean;
}
